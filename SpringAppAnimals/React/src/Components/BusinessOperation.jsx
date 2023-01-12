import React from 'react';
import {useForm} from "react-hook-form";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const BusinessOperation = () => {
    const { register, handleSubmit } = useForm();
    const navigate = useNavigate();

    async function onSubmit (data){
        const bad_food = await axios.get("http://localhost:8080/food/bad_food?type="+data.name);
        console.log(bad_food.data);
        for(let i = 0; i<bad_food.data[0].animals.length; i++){
            const exam = await axios.post("http://localhost:8080/examination", {
                date: new Date(2016,12,12,15,15,15),
                weight: 1,
                status: "Bad"
            });
            await axios.put("http://localhost:8080/examination/set_animal?examId="+exam.data.id+"&animalId="+bad_food.data[0].animals[i]);
        }
        await axios.delete("http://localhost:8080/food?id="+bad_food.data[0].id).then(() => navigate(-1));
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)} style={{display: "flex", flexDirection: "column", width: "20%", textAlign:"center"}}>
            <label>Bad food<input {...register("name")} /></label>
            <input type="submit" />
        </form>
    );
};

export default BusinessOperation;