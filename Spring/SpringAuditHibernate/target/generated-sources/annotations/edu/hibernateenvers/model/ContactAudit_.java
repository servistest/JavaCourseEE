package edu.hibernateenvers.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.joda.time.DateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactAudit.class)
public abstract class ContactAudit_ {

	public static volatile SingularAttribute<ContactAudit, String> firstName;
	public static volatile SingularAttribute<ContactAudit, String> lastName;
	public static volatile SingularAttribute<ContactAudit, DateTime> createdDate;
	public static volatile SingularAttribute<ContactAudit, String> createdBy;
	public static volatile SingularAttribute<ContactAudit, DateTime> lastModifiedDate;
	public static volatile SingularAttribute<ContactAudit, String> lastModifiedBy;
	public static volatile SingularAttribute<ContactAudit, Long> id;
	public static volatile SingularAttribute<ContactAudit, Date> birthDate;
	public static volatile SingularAttribute<ContactAudit, Integer> version;

}

