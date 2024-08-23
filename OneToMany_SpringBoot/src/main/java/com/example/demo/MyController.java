package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class MyController {
	@Autowired
	UserRepo userRepo;
	@Autowired
	TaskRepo taskRepo;
	
	@RequestMapping("deleteTask/{uid}/{tid}")
	public int delete1(@PathVariable int uid, @PathVariable int tid)
	{
		try
		{
			Optional<User> userOptional = userRepo.findById(uid);
		    if (userOptional.isPresent()) {
		        User user = userOptional.get();
		        List<Task> tasks = user.getTasks();
		        for (Task task : tasks) {
		            if (task.getId() == tid) {
		                tasks.remove(task);
		                userRepo.save(user);
		                return 1; // Task deleted successfully
		            }
		        }
		    }
		    return 0; 
	       
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
			
		}
	}
	
	@RequestMapping("add{id}")
	public Task add(@PathVariable int id,@RequestBody String details)
	{
		try {
			Task task=new Task();
			task.setDetails(details);
			Task taskdp=taskRepo.save(task);
			
			User user=userRepo.findById(id).get();
			user.getTasks().add(taskdp);
			userRepo.save(user);
			return taskdp;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	    @RequestMapping("readAllTask{id}")
	    public List<Task>readAllTask(@PathVariable int id)
		{
		try {
			Optional<User>obj=userRepo.findById(id);
	    	User user=obj.get();
	    	return user.getTasks();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	    @RequestMapping("login{username}")
	    public int login(@RequestBody String password,@PathVariable String username)
	    {
	    	try {
	    		int count=userRepo.countByUsername(username);
	    				if(count==0)
	    				{
	    					return -2;//wrong password
	    				}
	    				if(count>1)
	    				{
	    					return -3;//Multiple accounts on same username
	    				}
	    		    User user=userRepo.findByUsername(username);
	    		    if(user.getPassword().equals(password))
	    		
	    			return user.getId();	
	    	
	    	else
	    	{
	    		return -4;//wrong password
	    	
	    	}
	    		
				
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
	    	
	}
	    @RequestMapping("delete{uid}and{tid}")
	    public int delete(@PathVariable int uid, @PathVariable int tid)
	    {
	    try
	    {
		User user=userRepo.findById(uid).get();
		Task dltTask=null;
		
		for(Task task:user.getTasks())
		{
			if(task.getId()==tid)
			{
				dltTask=task;
				break;
			}
		}
		if(dltTask!=null)
		{
			user.getTasks().remove(dltTask);
			userRepo.save(user);
			return 1;
		}
		else
		{
			return 0;
		}
		} 
	    catch (Exception e) 
	    {
			e.printStackTrace();
			return -1;
		}	
	    }
	    
}