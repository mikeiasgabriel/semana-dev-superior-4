import NavBar from './components/NavBar';
import DataTable from './components/DataTable';
import Footer from './components/Footer';

function App() {
  return (
    <>
      <NavBar/>
      <div className="container">
        <DataTable />
      </div>
      <Footer/>
    </>
  );
}

export default App;
