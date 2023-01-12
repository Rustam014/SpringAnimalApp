import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import ExaminationService from "../../Services/ExaminationService";

const AllExaminations = () => {
    const [examinations, setExaminations] = useState(null);
    const [loaded, setLoaded] = useState(true);

    const deleteExamination = (id) => {
        ExaminationService.deleteExamination(id).then(()=>{
                const _exams = [...examinations].filter(exam => exam.id !== id);
            setExaminations(_exams);
            }
        )
    }

    async function getExams(){
        setLoaded(true);
        const _exams = await ExaminationService.getExaminations();
        setExaminations(_exams);
        setLoaded(false);
    }

    useEffect(() =>{
        getExams();
    }, [])

    return (
        <div>
            {loaded
                ? <h1>Loading</h1>
                : <div>
                    <h1>List of examinations</h1>
                    <table>
                        <tr>
                            <th>Date</th>
                            <th>Weight</th>
                            <th>Status</th>
                        </tr>
                        {examinations.map((exam) => {
                            return  <tr>
                                <td>{exam.date}</td>
                                <td>{exam.weight}</td>
                                <td>{exam.status}</td>
                                <td><Link to={"/examination/edit/"+ exam.id}><button>Edit</button></Link></td>
                                <td><button onClick={() => {deleteExamination(exam.id)}}>Delete</button></td>
                            </tr>;
                        })}
                    </table>
                    <Link to={"/examination/add/"}><button>Add examination</button></Link></div>
            }

        </div>
    );
};

export default AllExaminations;