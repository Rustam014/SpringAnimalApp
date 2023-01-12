import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from "react-router-dom";
import AnimalService from "../../Services/AnimalService";
import FoodService from "../../Services/FoodService";

const AllAnimals = () => {
    const [animals, setAnimals] = useState(null);
    const [loaded, setLoaded] = useState(true);
    const {food_id} = useParams();
    const navigate = useNavigate();

    const deleteAnimal = (id) => {
        AnimalService.deleteAnimal(id).then(()=>{
                const _animals = [...animals].filter(animal => animal.id !== id);
                setAnimals(_animals);
            }
        )
    }

    async function getAnimals(){
        setLoaded(true);
        const _animals = await AnimalService.getAnimals();
        setAnimals(_animals);
        setLoaded(false);
    }

    useEffect(() =>{
        getAnimals();
    }, [])

    const assignFood = (id) => {
        FoodService.feedAnimal(food_id,id).then(() => navigate(-1));
    }

    return (
        <div>
            {loaded
                ? <h1>Loading</h1>
                : <div>
                    <h1>List of animals</h1>
                    {food_id == null
                        ? <div>
                            <table>
                                <tr>
                                    <th>Name</th>
                                    <th>Gender</th>
                                    <th>Birthday</th>
                                    <th>Food</th>
                                    <th>Examinations</th>
                                </tr>
                                {animals.map((animal) => {
                                    return <tr>
                                        <td>{animal.name}</td>
                                        <td>{animal.gender}</td>
                                        <td>{animal.birthday}</td>
                                        <td>{animal.food}</td>
                                        <td>{animal.exams}</td>
                                        <td><Link to={"/animal/edit/" + animal.id}>
                                            <button>Edit</button>
                                        </Link></td>
                                        <td>
                                            <button onClick={() => {
                                                deleteAnimal(animal.id)
                                            }}>Delete
                                            </button>
                                        </td>
                                    </tr>;
                                })}
                            </table>
                            <Link to={"/animal/add/"}>
                                <button>Add animal</button>
                            </Link></div>
                        : <div>
                            <table>
                                <tr>
                                    <th>Name</th>
                                    <th>Gender</th>
                                    <th>Birthday</th>
                                </tr>
                                {animals.map((animal) => {
                                    return <tr>
                                        <td>{animal.name}</td>
                                        <td>{animal.gender}</td>
                                        <td>{animal.birthday}</td>
                                        <td>
                                            <button onClick={() => assignFood(animal.id)}>Assign</button>
                                        </td>
                                    </tr>;
                                })}
                            </table>
                        </div>
                    }
                    </div>
            }
        </div>
    );
};

export default AllAnimals;