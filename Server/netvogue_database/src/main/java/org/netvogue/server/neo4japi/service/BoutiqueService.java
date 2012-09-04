package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ProductLines;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.neo4japi.domain.User;

public interface BoutiqueService {
	public User AuthenticateUser(String email, String password);
	public ResultStatus AddNewBoutique(Boutique b, String error);
	public Iterable<Boutique> GetBoutique(String boutiqueName);
	public User GetUser(String name);
	public User GetOrCreateUser(String name);
	public ResultStatus ValidateEmail(String email);
	public ResultStatus ValidateUsername(String username);
	public ResultStatus deleteAll();
	
	//Category related
	public Category getCategory(ProductLines name);
	//Make sure you do template.save or repo.save
	public Category getOrCreateCategory(ProductLines name);
}
