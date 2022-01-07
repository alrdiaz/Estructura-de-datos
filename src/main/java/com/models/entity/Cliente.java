package com.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="clientes")
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Cliente {
	
	@Id	
	private Long nmid;
	
	@NotNull
	private int cus_nmcliente;
	
	@NotEmpty
	@Column(length = 120)
	private String cus_dsnombres;
	
	@NotEmpty
	@Column(length = 120)
	private String cus_dsapellidos;
	
	@Column(length = 120)
	private String cus_dsdireccion;
	
	@NotEmpty
	@Column(length = 120)
	@Email
	private String cus_dscorreo;
	
	@Column(length = 20)
	private String cus_cdtelefono;
	
	@Column(length = 20)
	private String cus_cdtelefonoalter;
	
	@NotEmpty
	@Column(length = 20)
	private String cus_cdcelular;
	
	private int cus_nmcargo;
	
	@Column(length = 120)
	private String cus_dscargo;
	
	private int cus_nmciudad; //se asume ZIP CODE
	
	@Column(length = 60)
	private String cus_dsciudad;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date cus_fenacimiento; 
	
	@NotEmpty
	@Column(length = 1)
	private String cus_genero; //M masculino F femenino O otro
	
	private int cus_nmcomunidad;// se asume numero de comuna
	
	@Column(length = 120)
	private String cus_dscomunidad;
	
	@Column(length = 200)
	private String cus_dsempresalabora;
	
	private int cus_nmdivision;
	
	@Column(length = 120)
	private String cus_dsdivision;
	
	private int cus_nmpais;
	
	@NotEmpty
	@Column(length = 120)
	private String cus_dspais;
	
	@Column(columnDefinition="text")
	private String cus_hobbies;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date cus_febaja; 
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date cus_feregistro; 
	
//	@PrePersist
//	public void prePersist() {
//		cus_feregistro=new Date();
//		System.out.println(cus_feregistro);		
//	}
	
	public Boolean isValid(String regex, String value, Integer lenght) {
        if (value == null) {
            return false;
        }
        if (lenght != null && value.length() > lenght) {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }
	
	

    public String validate() {
//    	SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    	
        if (!isValid("^\\d{1,15}$", this.nmid.toString(),15)) {
            return "La cédula del cliente sólo puede contener números, máximo 15 caracteres sin espacios o puntos";
        }else if (!isValid("^\\d*{1,}$", Integer.toString(this.cus_nmcliente), 10)) {
            return "Número cliente inválido, sólo se permiten entre 1 y 10 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,120}$", this.cus_dsnombres, 120)) {
            return "Nombre inválido, sólo se permiten entre 1 y 120 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,120}$", this.cus_dsapellidos, 120)) {
            return "Apellido inválido, sólo se permiten entre 1 y 120 caracteres";
        } else if (!isValid(".{1,120}$", this.cus_dsdireccion, 120)) {
            return "Dirección inválida, sólo se permiten entre 1 y 120 caracteres";
        } else if (!isValid("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", this.cus_dscorreo, 120)) {
            return "Correo electrónico inválido, se permiten hata 120 caracteres, sin espacios";
        } else if (!isValid(".{8,20}$", this.cus_cdtelefono, 20)) {
            return "El teléfono debe contener entre 8 y 20 caracteres.";
        } else if (!isValid(".{8,20}$", this.cus_cdtelefonoalter, 20)) {
            return "El teléfono alterno debe contener entre 8 y 20 caracteres.";
        }else if (!isValid(".{8,20}$", this.cus_cdcelular, 20)) {
            return "El teléfono celular debe contener entre 8 y 20 caracteres.";       
        }else if (!isValid("^\\d*{1,}$", Integer.toString(this.cus_nmcargo), 10)) {
            return "Número cargo cliente inválido, sólo se permiten entre 1 y 10 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,120}$", this.cus_dscargo, 120)) {
            return "Cargo inválido, sólo se permiten entre 1 y 120 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,60}$", this.cus_dsciudad, 60)) {
            return "Ciudad inválida, sólo se permiten entre 1 y 60 caracteres";
        }else if (!isValid("^\\d*{1,}$", Integer.toString(this.cus_nmciudad), 10)) {
            return "Número ciudad cliente inválido, sólo se permiten entre 1 y 10 caracteres";
        
        }else if (!isValid("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$",date.format(this.cus_fenacimiento), 10)) {
           return "Fecha nacimiento inválida, sólo se permite formato yyyy-MM-dd de 10 caracteres";       
        }
        else if (!isValid("M|F|O{1}", this.cus_genero, 1)) {
            return "Genero inválido, sólo se permite 1 caracter en Mayuscula M,F y O";          
        }else if (!isValid("^\\d*{1,}$", Integer.toString(this.cus_nmcomunidad), 10)) {
            return "Número comunidad cliente inválido, sólo se permiten entre 1 y 10 caracteres";
        }else if (!isValid(".{1,120}$", this.cus_dscomunidad, 120)) {
            return "Comunidad inválida, sólo se permiten entre 1 y 120 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,200}$", this.cus_dsempresalabora, 200)) {
            return "Empresa que labora inválida, sólo se permiten entre 1 y 200 caracteres";
        }else if (!isValid("^\\d*{1,}$", Integer.toString(this.cus_nmdivision), 10)) {
            return "Número división cliente inválido, sólo se permiten entre 1 y 10 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,120}$", this.cus_dsdivision, 120)) {
            return "División inválida, sólo se permiten entre 1 y 120 caracteres";
        }else if (!isValid("^\\d*{1,}$", Integer.toString(this.cus_nmpais), 10)) {
            return "Número país cliente inválido, sólo se permiten entre 1 y 10 caracteres";
        }else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{1,120}$", this.cus_dspais, 120)) {
            return "País inválido, sólo se permiten entre 1 y 120 caracteres";
        }else if (!isValid(".{0,65535}$", this.cus_hobbies, 65535)) {
            return "Hobbies inválido, sólo se permiten hasta 65535 caracteres";
//        }else if (!isValid("((((19|20)([2468][048]|[13579][26]|0[48])|2000)-02-29|((19|20)[0-9]{2}-(0[4678]|1[02])-(0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}-(0[1359]|11)-(0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}-02-(0[1-9]|1[0-9]|2[0-8])))\\s([01][0-9]|2[0-3]):([012345][0-9]):([012345][0-9]))", timeStamp.format(this.cus_febaja), 19)) {
//            return "Fecha de baja inválida, sólo se permite formato YYYY-MM-DD HH:MM:SS de 19 caracteres";       
//        }else if (!isValid("((((19|20)([2468][048]|[13579][26]|0[48])|2000)-02-29|((19|20)[0-9]{2}-(0[4678]|1[02])-(0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}-(0[1359]|11)-(0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}-02-(0[1-9]|1[0-9]|2[0-8])))\\s([01][0-9]|2[0-3]):([012345][0-9]):([012345][0-9]))", timeStamp.format(this.cus_feregistro), 19)) {
//            return "Fecha de registro inválida, sólo se permite formato YYYY-MM-DD HH:MM:SS de 19 caracteres";       
        }   
        return null;
    }





}
