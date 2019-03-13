package my.service.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
   private static final long serialVersionUID = 1L;

   private EntityManager entityManager;

   public MyDataDaoImpl(){
       super();
   }
   public MyDataDaoImpl(EntityManager manager){
       this();
       entityManager = manager;
   }

   @Override
    public List<MyData> getAll() {
       List<MyData> list = null;
       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
       CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
       Root<MyData> root = query.from(MyData.class);
       query.select(root);
       list = (List<MyData>)entityManager
               .createQuery(query)
               .getResultList();
       return list;
   }

    @Override
    public MyData findById(long id) {
        return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MyData> findByName(String name) {
        return (List<MyData>)entityManager.createQuery("from MyData where name =" + name).getSingleResult();
    }

    @Override
    public List<MyData> findByAge(int min, int max) {
        return (List<MyData>)entityManager
                .createNamedQuery("findByAge")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    public List<MyData> find(String fstr) {
       List<MyData> list = null;
       Long fid = 0L;
       try {
           fid = Long.parseLong(fstr);
       } catch (NumberFormatException e) {
           //e.printStackTrace();
       }
       Query query = entityManager
               .createNamedQuery("findWithName")
               .setParameter("fname", "%" + fstr + "%");
       list = query.getResultList();
       return list;
    }
}
