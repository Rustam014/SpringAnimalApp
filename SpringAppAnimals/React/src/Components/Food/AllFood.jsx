import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import FoodService from "../../Services/FoodService";

const AllFood = () => {
    const [food, setFood] = useState(null);
    const [loaded, setLoaded] = useState(true);

    const deleteFood = (id) => {
        FoodService.deleteFood(id).then(()=>{
                const _food = [...food].filter(food => food.id !== id);
            setFood(_food);
            }
        )
    }

    async function getFood(){
        setLoaded(true);
        const _food = await FoodService.getAllFood();
        setFood(_food);
        setLoaded(false);
    }

    useEffect(() =>{
        getFood();
    }, [])

    return (
        <div>
            {loaded
                ? <h1>Loading</h1>
                : <div>
                    <h1>List of food</h1>
                    <table>
                        <tr>
                            <th>Type</th>
                            <th>Date</th>
                            <th>Supply</th>
                        </tr>
                        {food.map((food) => {
                            return  <tr>
                                <td>{food.type}</td>
                                <td>{food.date}</td>
                                <td>{food.supply}</td>
                                <td><Link to={"/food/edit/"+ food.id}><button>Edit</button></Link></td>
                                <td><button onClick={() => {deleteFood(food.id)}}>Delete</button></td>
                                <td><Link to={"/food/"+food.id+"/assign/"}><button>Assign</button></Link></td>
                            </tr>;
                        })}
                    </table>
                    <Link to={"/food/add/"}><button>Add food</button></Link></div>
            }

        </div>
    );
};

export default AllFood;