/*
 Version Number 0.0.1
*/
 
package com.vi.model;
 
import lombok.*;
import lombok.experimental.SuperBuilder;
 
import java.util.Date;
 
@Data
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private Long id;
 
    private Date created;
 
    private Date modified = new Date();
 
    private Boolean active = true;
   
    private Boolean deleted = false;
 
    private Long createdBy;
   
    private Long modifiedBy;
}
 