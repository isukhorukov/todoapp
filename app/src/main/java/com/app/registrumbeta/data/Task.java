package com.app.registrumbeta.data;

public class Task {
	
	//values
    private long id;
    private String task;
	private int importantCriterion;
	private int quickCriterion;
	private int clearCriterion;
	private int doneStatus;

	//getters-setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
	
	public int getImportantCriterion() {
		return importantCriterion;
	}
	
	public void setImportantCriterion(int importantCriterion) {
		this.importantCriterion = importantCriterion;
	}
	
	public int getQuickCriterion() {
		return quickCriterion;
	}
	
	public void setQuickCriterion(int quickCriterion) {
		this.quickCriterion = quickCriterion;
	}
	
	public int getClearCriterion() {
		return clearCriterion;
	}
	
	public void setClearCriterion(int clearCriterion) {
		this.clearCriterion = clearCriterion;
	}
	
	public int getDoneStatus() {
		return doneStatus;
	}
	
	public void setDoneStatus(int doneStatus) {
		this.doneStatus = doneStatus;
	}
	
    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return task;
    }
}
