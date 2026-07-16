package co.edu.unbosque.model.persistence;

public interface OperacionCRUD<E, D> {

	public void crear(D nuevoDato);

	public String mostrar();

	public boolean eliminar(int index);

	public boolean actualizar(int index, D datoActualizado);

	public int contar();

}
