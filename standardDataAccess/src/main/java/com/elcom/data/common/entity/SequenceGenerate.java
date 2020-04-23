/**
 * 
 */
package com.elcom.data.common.entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * @author anhdv.ho
 *
 */
public class SequenceGenerate implements IdentifierGenerator, Configurable {

	private String seqName;
	
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		Connection connection = session.connection();
		ResultSet rs = null;
		Statement statement = null;
		
		try {
			
			statement = connection.createStatement();
			
			//System.out.println("Sequence Name: " +seqName);
			
			rs = statement.executeQuery(MessageFormat.format("select {0} from DUAL", seqName));

			if (rs.next())
				return rs.getLong(1);

		} catch (SQLException e) {
			
			throw new HibernateException("SequenceObject.generate.ex[37]: " + e.toString());
			
		} finally {
			
			try {
				
				if( rs != null && !rs.isClosed() )
					rs.close();
				
				if( statement != null && !statement.isClosed() )
					statement.close();
				
				if( connection != null && !connection.isClosed() )
					connection.close();
				
			} catch (SQLException e) {
				throw new HibernateException("SequenceObject.generate.ex[47]: " + e.toString());
			}
		}

		return null;
	}
	
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		setSeqName(params.getProperty("seqName"));
	}
	
	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
}
