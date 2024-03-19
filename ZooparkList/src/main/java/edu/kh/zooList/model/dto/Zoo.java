package edu.kh.zooList.model.dto;

public class Zoo {

	private int zooNo;       //번호
	private String zooLocation; //구역
	private String zooAnimal; //동물
	private int animalCount;  //수
	
	
	public Zoo() {
		
	}
	
	
	//매개변수생성자
	public Zoo(int zooNo, String zooLocation, String zooAnimal, int animalCount) {
	super();
	this.zooNo = zooNo;
	this.zooLocation = zooLocation;
	this.zooAnimal = zooAnimal;
	this.animalCount = animalCount;
}


	public int getZooNo() {
		return zooNo;
	}


	public void setZooNo(int zooNo) {
		this.zooNo = zooNo;
	}


	public String getZooLocation() {
		return zooLocation;
	}


	public void setZooLocation(String zooLocation) {
		this.zooLocation = zooLocation;
	}


	public String getZooAnimal() {
		return zooAnimal;
	}


	public void setZooAnimal(String zooAnimal) {
		this.zooAnimal = zooAnimal;
	}


	public int getAnimalCount() {
		return animalCount;
	}


	public void setAnimalCount(int animalCount) {
		this.animalCount = animalCount;
	}
	
	
	
	
	
}
