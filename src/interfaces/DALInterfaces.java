package interfaces;

import java.util.List;

public interface DALInterfaces<T> {//b�t�n dal katman�n� tek interf.�zerinden yapmak istedi�imden generic yapt�m
	
	 void Insert(T entity); //verit. na kay�t atmak i�in
	 List<T> GetAll();//b�t�n verileri listelemek i�in
	 T Delete(T entity);
	 T Update(T entity);
	 List<T> GetById(int id);//paramtreli listelemek i�in

}
