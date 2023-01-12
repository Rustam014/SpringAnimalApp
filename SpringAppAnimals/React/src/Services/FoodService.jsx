import axios from "axios";

export default class FoodService{
    static async getAllFood(){
        try{
            const response = await axios.get("http://localhost:8080/food");
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async getFood(id){
        try{
            const response = await axios.get("http://localhost:8080/food?id=" + id);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async addFood(type,date,supply){
        try{
            const response = await axios.post("http://localhost:8080/food",{
                type: type,
                date: date,
                supply: supply
            });
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async updateFood(id, type,date,supply){
        try{
            const response = await axios.put("http://localhost:8080/food?id=" + id,{
                type: type,
                date: date,
                supply: supply
            });
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async deleteFood(id){
        try{
            const response = await axios.delete("http://localhost:8080/food?id=" + id);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async feedAnimal(foodId, animalId){
        try{
            const response = await axios.put("http://localhost:8080/food/feed?foodId="+foodId+"&animalId=" + animalId);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }
};
