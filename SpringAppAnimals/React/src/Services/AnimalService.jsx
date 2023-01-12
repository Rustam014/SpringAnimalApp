import axios from "axios";

export default class AnimalService{
    static async getAnimals(){
        try{
            const response = await axios.get("http://localhost:8080/animal");
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async getAnimal(id){
        try{
            const response = await axios.get("http://localhost:8080/animal?id=" + id);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async addAnimal(name,gender,birthday){
        try{
            const response = await axios.post("http://localhost:8080/animal",{
                name: name,
                gender: gender,
                birthday: birthday
            });
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async updateAnimal(id, name,gender,birthday){
        try{
            const response = await axios.put("http://localhost:8080/animal?id=" + id,{
                name: name,
                gender: gender,
                birthday: birthday
            });
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }

    static async deleteAnimal(id){
        try{
            const response = await axios.delete("http://localhost:8080/animal?id=" + id);
            console.log(response)
            return response.data;
        }
        catch (e){
            console.log(e);
        }
    }
};
