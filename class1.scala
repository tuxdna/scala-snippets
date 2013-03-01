class class1 {
  private var privateAge = 0 // Make private and rename
  def age = privateAge
  def age_=(newValue: Int) {
    if (newValue > privateAge) privateAge = newValue; // Can’t get younger
  }
}
