package com.spring.projeto.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent{


	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private Long id;
	public RecursoCriadoEvent(Object source, HttpServletResponse response , Long id) {
		super(source);
		
		this.id = id;
		this.response = response;
		
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public Long getId() {
		return id;
	}
	
	

}
