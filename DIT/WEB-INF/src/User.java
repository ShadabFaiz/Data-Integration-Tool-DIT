package org.newuser;


public class User
{
	private String username=null;
	private String password=null;
	private String email=null;
	private String occupation=null;
	
	private void setUsername(String name)
	{	this.username=name;
		}
	private String getUsername()
	{	return this.username;
		}
		
	
	private void setPassword(String pass)
	{
		this.password=pass;
		}
	private String getPassword()
	{	return this.password;
		}

		
	private void setEmail(String em)
	{	this.email=em;
		}
	private String getEmail()
	{		return this.email;
		}
		
	
	private void setOccupation(String oc)
	{	this.occupation=oc;
		}

	private String getOccupation()
	{	return this.occupation;
		}
}