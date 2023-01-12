import React from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {useForm} from "react-hook-form";
import FoodService from "../../Services/FoodService";

const AddFood = () => {
    const { register, handleSubmit } = useForm();

    const navigate = useNavigate();
    const {id} = useParams();
    const onSubmit = (data) => {
        console.log(data);
        if(id == null){
            FoodService.addFood(data.type, data.date, data.supply).then(() => navigate(-1));
        }
        else {
            FoodService.updateFood(id, data.type, data.date, data.supply).then(() => navigate(-1));
        }
    };
    return (
        <form onSubmit={handleSubmit(onSubmit)} style={{display: "flex", flexDirection: "column", width: "20%", textAlign:"center"}}>
            <label>Type <input {...register("type")} /></label>
            <label>Supply <input {...register("supply")} /></label>
            <label>Date <input type="datetime-local" name="datetime" {...register("date")} /></label>
            <input type="submit" />
        </form>
    );
};

export default AddFood;