package encoway.springframework.videoapplication.exception;

public  class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName(){
        return resourceName;
    }


    public long getFieldValue(){
        return fieldValue;
    }
}
