package com.orchestra.portale.persistence.sql.repositories;
import com.orchestra.portale.persistence.sql.entities.Cart;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andzaccaro
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Iterable<Cart> findCartByIdUser(int idUser);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("delete from Cart c where c.idUser = ?1")
    void deleteCart(Integer id_user); 
}
