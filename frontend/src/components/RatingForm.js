import { Form } from "react-bootstrap";
import { useForm } from "react-hook-form";
import {useNavigate} from "react-router-dom";
const errorMessageStyle = {color: 'red', float: 'right'};
function RatingForm({onRatingSent}) {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const navigate = useNavigate();

    const onSubmit = data => {
        onRatingSent(data.rating);
    };

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group >
                <Form.Label>Rating:</Form.Label>
                <Form.Select {...register("rating", {required: true})}>
                    <option value="">Select a rating</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </Form.Select>
                {errors.rating && <Form.Text style={errorMessageStyle}>A rating is required</Form.Text>}
            </Form.Group>
            <button className="abutton" type="submit"
                    onClick={() => navigate(-1)}>
                Send
            </button>
        </Form>
    )
}

export default RatingForm;