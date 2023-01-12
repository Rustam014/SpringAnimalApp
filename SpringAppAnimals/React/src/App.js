import './App.css';
import {Route, Routes} from "react-router-dom";
import {Layout} from "./Components/Layout";
import Mainpage from "./Mainpage";
import AllAnimals from "./Components/Animals/AllAnimals";
import AddAnimal from "./Components/Animals/AddAnimal";
import BusinessOperation from "./Components/BusinessOperation";
import AllExaminations from "./Components/Examinations/AllExaminations";
import AddExamination from "./Components/Examinations/AddExamination";
import AllFood from "./Components/Food/AllFood";
import AddFood from "./Components/Food/AddFood";

function App() {
  return (
    <div className="App">
      <Routes path="/" element={<Layout />}>
          <Route index element={<Mainpage />}/>
          <Route path="animal/" element={<AllAnimals />}/>
          <Route path="animal/add/" element={<AddAnimal />}/>
          <Route path="animal/edit/:id" element={<AddAnimal />}/>
          <Route path="examination/" element={<AllExaminations />}/>
          <Route path="examination/add/" element={<AddExamination />}/>
          <Route path="examination/edit/:id" element={<AddExamination />}/>
          <Route path="food/" element={<AllFood />}/>
          <Route path="food/add/" element={<AddFood />}/>
          <Route path="food/edit/:id" element={<AddFood />}/>
          <Route path="food/:food_id/assign" element={<AllAnimals />}/>
          <Route path="business/" element={<BusinessOperation />}/>
      </Routes>
    </div>
  );
}

export default App;
