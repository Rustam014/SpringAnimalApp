import React from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {useForm} from "react-hook-form";
import ExaminationService from "../../Services/ExaminationService";

const AddExamination = () => {
    const { register, handleSubmit } = useForm();

    const navigate = useNavigate();
    const {id} = useParams();
    const onSubmit = (data) => {
        console.log(data);
        if(id == null){
            ExaminationService.addExamination(data.date, data.weight, data.status).then(() => navigate(-1));
        }
        else {
            ExaminationService.updateExamination(id, data.date, data.weight, data.status).then(() => navigate(-1));
        }
    };
    return (
        <form onSubmit={handleSubmit(onSubmit)} style={{display: "flex", flexDirection: "column", width: "20%", textAlign:"center"}}>
            <label>Status <input {...register("status")} /></label>
            <label>Weight <input {...register("weight")} /></label>
            <label>Date <input type="datetime-local" name="datetime" {...register("date")} /></label>
            <input type="submit" />
        </form>
    );
};

export default AddExamination;