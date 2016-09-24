package edu.springjpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactTelDetail.class)
public abstract class ContactTelDetail_ {

	public static volatile SingularAttribute<ContactTelDetail, String> tel_number;
	public static volatile SingularAttribute<ContactTelDetail, String> tel_type;
	public static volatile SingularAttribute<ContactTelDetail, Contact> contact;
	public static volatile SingularAttribute<ContactTelDetail, Integer> id;
	public static volatile SingularAttribute<ContactTelDetail, Integer> version;

}

