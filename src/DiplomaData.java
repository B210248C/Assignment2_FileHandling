public class DiplomaData {

    private String catagory;
    private String name;
    private String total;
    private String max;
    private String min;

    public DiplomaData(String catagory,String name,String total,String max,String min) {
        this.catagory = catagory;
        this.name = name;
        this.total = total;
        this.max = max;
        this.min = min;

    }
    @Override
    public String toString() {
        return "Category = "+catagory+", Name = "+name+", Total = "+total+", Maximum = "+max+", Minimum = "+min;
    }

    public String getCatagory() {
        return catagory;
    }

    public String getName() {
        return name;
    }

    public String getTotal() {
        return total;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
