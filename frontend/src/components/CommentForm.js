import { Form } from "react-bootstrap";
import { useForm } from "react-hook-form";
import {useNavigate} from "react-router-dom";

const errorMessageStyle = {color: 'red', float: 'right'};

function CommentForm({onCommentSent}) {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const navigate = useNavigate();

    const onSubmit = data => {
        onCommentSent(data.comment);
    };

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group className="mb-3">
                <Form.Label>Comment:</Form.Label>
                <input className="form-control"
                       type="text"
                       {...register("comment", {
                           minValue: {value: 3, message: "Minimum is 3 characters"},
                           maxValue: {value: 150, message: "Maximum is 150 characters"},
                           required: {value: true, message: "A message is required"}
                       })}
                       placeholder="Enter your comment message."/>
                <Form.Text style={errorMessageStyle}>
                    {errors.comment?.message}
                </Form.Text>
            </Form.Group>
            <button className="abutton" type="submit"
                    onClick={() => navigate(-1)}>
                Send
            </button>
        </Form>
    )
}

export default CommentForm;

