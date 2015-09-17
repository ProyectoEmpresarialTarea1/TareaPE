package pe.edu.cibertec.proyemp.jpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JpaTest {
	
	
	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();	
			test.crearEmpleados();	
			test.modificarDepartamentoId1();
		tx.commit();
		
		test.listarEmpleados();
	}
	
	private void modificarDepartamentoId1(){
		/*String jql="select d from Departamento d"+" where d.id=1";
		String jql2="from Departamento where id=1";
		
		Departamento dep=manager.createQuery(jql,Departamento.class).getSingleResult();*/
		
		Departamento dep =manager.find(Departamento.class,new Long( 1));
		
		dep.setNombre(".Net");
		manager.persist(dep);
		
	}

	private void crearEmpleados() {
	
		Departamento departamento = new Departamento("Java");
		manager.persist(departamento);

		Empleado emp1 = new Empleado("Bob",departamento);
		Empleado emp2 = new Empleado("Mike",departamento);
		
		manager.persist(emp1);
		manager.persist(emp2);
		
		manager.refresh(emp1);
		emp1.setNombre("Boby");
		
		
	}
	
	private void crearEmpleados2() {
		
		Departamento departamento =
				new Departamento("Java");
		
		Empleado emp1 = new Empleado("Bob",departamento);
		Empleado emp2 = new Empleado("Mike",departamento);
		
//		List<Empleado> empleados =
//				new ArrayList<Empleado>();
//		empleados.add(emp1);
//		empleados.add(emp2);
		
		departamento.setEmpleados(Arrays.asList(emp1,emp2));
		manager.persist(emp1);
		manager.persist(emp2);
	}
	
	private void listarEmpleados() {
		List<Empleado> resultList =
				manager.createQuery(
						"Select a From Empleado a", 
						Empleado.class).getResultList();
		
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado next : resultList) {
			System.out.println("siguiente empleado: " + next);
		}
	}


}
