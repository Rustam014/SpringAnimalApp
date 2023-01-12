import axios from "axios";

export default class ExaminationService{
    static async getExaminations(){
        try{
            const response = await axios.get("http://localhost:8080/examination");
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async getExamination(id){
        try{
            const response = await axios.get("http://localhost:8080/examination?id=" + id);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async addExamination(date,weight,status){
        try{
            const response = await axios.post("http://localhost:8080/examination",{
                date: date,
                weight: weight,
                status: status
            });
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async updateExamination(id, date,weight,status){
        try{
            const response = await axios.put("http://localhost:8080/examination?id=" + id,{
                date: date,
                weight: weight,
                status: status
            });
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async deleteExamination(id){
        try{
            const response = await axios.delete("http://localhost:8080/examination?id=" + id);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async setExamination(examId, animalId){
        try{
            const response = await axios.put("http://localhost:8080/examination/set_animal?examId="+examId+"&animalId=" + animalId);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }
};