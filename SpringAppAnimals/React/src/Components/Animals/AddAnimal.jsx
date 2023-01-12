import React from 'react';
import {useNavigate, useParams} from "react-router-dom";
import AnimalService from "../../Services/AnimalService";
import {useForm} from "react-hook-form";

const AddAnimal = () => {
    const { register, handleSubmit } = useForm();

    const navigate = useNavigate();
    const {id} = useParams();
    const onSubmit = (data) => {
        console.log(data);
        if(id == null){
            AnimalService.addAnimal(data.name, data.gender, data.birthday).then(() => navigate(-1));
        }
        else {
            AnimalService.updateAnimal(id, data.name, data.gender, data.birthday).then(() => navigate(-1));
        }
    };
    return (
        <form onSubmit={handleSubmit(onSubmit)} style={{display: "flex", flexDirection: "column", width: "20%", textAlign:"center"}}>
            <label>Name <input {...register("name")} /></label>
            <label>Gender <input {...register("gender")} /></label>
            <label>Birthday <input type="datetime-local" name="datetime" {...register("birthday")} /></label>
            <input type="submit" />
        </form>
    );
};

export default AddAnimal;