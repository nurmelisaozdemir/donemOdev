package interfaces;

import java.util.List;

public interface DALInterfaces<T> {//bütün dal katmanýný tek interf.üzerinden yapmak istediðimden generic yaptým
	
	 void Insert(T entity); //verit. na kayýt atmak için
	 List<T> GetAll();//bütün verileri listelemek için
	 T Delete(T entity);
	 T Update(T entity);
	 List<T> GetById(int id);//paramtreli listelemek için

}
