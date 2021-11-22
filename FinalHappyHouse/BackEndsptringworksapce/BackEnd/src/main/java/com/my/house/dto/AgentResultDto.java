package com.my.house.dto;

import java.util.List;

public class AgentResultDto {
	private int result;
	private List<AgentDto> list;
	private AgentDto agentDto;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public AgentDto getAgentDto() {
		return agentDto;
	}
	public void setAgentDto(AgentDto agentDto) {
		this.agentDto = agentDto;
	}
	public List<AgentDto> getList() {
		return list;
	}
	public void setList(List<AgentDto> list) {
		this.list = list;
	}
	
	
	
	
	

	
	
	

}
