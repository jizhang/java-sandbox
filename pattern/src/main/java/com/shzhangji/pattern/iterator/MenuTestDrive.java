package com.shzhangji.pattern.iterator;

public class MenuTestDrive {
  public static void main(String[] args) {
    var pancakeHouseMenu = createPancakeHouseMenu();
    var dinerMenu = createDinerMenu();
    var cafeMenu = createCafeMenu();

    var allMenus = new Menu("ALL MENUS", "All menus combined");
    allMenus.add(pancakeHouseMenu);
    allMenus.add(dinerMenu);
    allMenus.add(cafeMenu);

    var waitress = new Waitress(allMenus);
    waitress.printMenu();
  }

  static Menu createPancakeHouseMenu() {
    var pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
    pancakeHouseMenu.add(new MenuItem("K&B's Pancake Breakfast",
        "Pancakes with scrambled eggs, and toast",
        true, 2.99));
    pancakeHouseMenu.add(new MenuItem("Regular Pancake Breakfast",
        "Pancakes with fried eggs, sausage",
        false, 2.99));
    pancakeHouseMenu.add(new MenuItem("Blueberry Pancakes",
        "Pancakes made with fresh blueberries",
        true, 3.49));
    pancakeHouseMenu.add(new MenuItem("Waffles",
        "Waffles, with your choice of blueberries or strawberries",
        true, 3.59));
    return pancakeHouseMenu;
  }

  static Menu createDinerMenu() {
    var dinerMenu = new Menu("DINER MENU", "Lunch");
    var iterator = new DinerMenu().createIterator();
    while (iterator.hastNext()) {
      dinerMenu.add(iterator.next());
    }

    dinerMenu.add(createDessertMenu());
    return dinerMenu;
  }

  static Menu createCafeMenu() {
    var cafeMenu = new Menu("CAFE MENU", "Dinner");
    cafeMenu.add(new MenuItem("Veggie Burger and Air Fries",
        "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
        true, 3.99));
    cafeMenu.add(new MenuItem("Soup of the day",
        "A cup of the soup of the day, with a side salad",
        false, 3.69));
    cafeMenu.add(new MenuItem("Burrito",
        "A large burrito, with whole pinto beans, salsa, guacamole",
        true, 4.19));
    return cafeMenu;
  }

  static Menu createDessertMenu() {
    var dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");
    dessertMenu.add(new MenuItem("Apple Pie",
        "Apple pie with a flakey crust, topped with vanilla icecream",
        true, 1.59));
    dessertMenu.add(new MenuItem("Cheesecake",
        "Creamy New York cheesecake, with a chocolate graham crust",
        true, 1.99));
    return dessertMenu;
  }
}
