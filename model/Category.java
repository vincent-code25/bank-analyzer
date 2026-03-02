package RoadmapForJava2026.model;
public enum Category {
   FOOD("Food"),
   RENT("Rent"),
   SALARY("Salary"),
   ENTERTAINMENT("Entertainment"),
   OTHER("Other");

   private final String label;

   Category(String label) {
      this.label = label;
   }

   public String getLabel() {
      return label;
   }
}