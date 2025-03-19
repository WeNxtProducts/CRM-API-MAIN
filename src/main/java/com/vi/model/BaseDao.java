/*
 Version Number 0.0.1
*/
 
package com.vi.model;
 
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import jakarta.persistence.*;
import java.util.*;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    @CreatedBy
    @Column(name="created_by", nullable = false)
    private Long createdBy;
 
    @LastModifiedBy
    @Column(name="modified_by", nullable = false)
    private Long modifiedBy;
 
    @Column(name="active", nullable = false)
    private Boolean active;
 
    @Column(name="created", nullable = false)
    private Date created =new Date();
 
    @Column(name="modified", nullable = false)
    private Date modified = new Date();
 
    @Column(name="deleted", nullable = false)
    private Boolean deleted=false;
}
 