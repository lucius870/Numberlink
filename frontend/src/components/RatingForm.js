import { Form } from "react-bootstrap";
import { useForm } from "react-hook-form";
import {useNavigate} from "react-router-dom";
import './Component.css';
const errorMessageStyle = {color: 'red', float: 'right'};
function RatingForm({onRatingSent}) {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const navigate = useNavigate();

    const onSubmit = data => {
        onRatingSent(data.rating);
        navigate(-1)
    };

    return (
        <div className="rating-form-container">
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group className="rating-form" >
                <Form.Select {...register("rating", {required: true})}>
                    <option value="">Select a rating</option>
                    <option value="1">&#9733;</option>
                    <option value="2">&#9733;&#9733;</option>
                    <option value="3">&#9733;&#9733;&#9733;</option>
                    <option value="4">&#9733;&#9733;&#9733;&#9733;</option>
                    <option value="5">&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                </Form.Select>
                {errors.rating && <Form.Text style={errorMessageStyle}>A rating is required</Form.Text>}
            </Form.Group>
            <button className="ratingButton" type="submit"
                    disabled={!!errors.comment}>
                <p className="send">Send</p>
            </button>
        </Form>
        </div>
    )
}

export default RatingForm;