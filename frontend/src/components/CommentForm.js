import { Form } from "react-bootstrap";
import { useForm } from "react-hook-form";
import {useNavigate} from "react-router-dom";
import './Component.css';

const errorMessageStyle = {color: 'red', float: 'right'};
function CommentForm({ onCommentSent }) {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const navigate = useNavigate();

    const onSubmit = data => {
        onCommentSent(data.comment);
        navigate(-1); // Navigate back after submitting comment
    };

    return (
        <div className="comment-form-container">
            <Form onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="comment-form">
                    <input
                        className="form-control"
                        type="text"
                        {...register("comment", {
                            minLength: { value: 3, message: "Minimum is 3 characters" },
                            maxLength: { value: 150, message: "Maximum is 150 characters" },
                            required: { value: true, message: "A message is required" }
                        })}
                        placeholder="Enter your comment message."
                    />
                    <Form.Text style={errorMessageStyle}>
                        {errors.comment && errors.comment.message}
                    </Form.Text>
                </Form.Group>
                <button
                    className="sendCommentButton"
                    type="submit"
                    disabled={!!errors.comment}
                >
                    <p className="send">Send</p>
                </button>
            </Form>
        </div>
    )
}

export default CommentForm;
