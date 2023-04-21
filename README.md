# Task 2.3.1 Crud-app

Не понял как правильно использововать @RequesBody
в контроллере. 

```java
    @PostMapping("/users/add")
    public String saveUserPost(@RequesBody User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
```
код валился с 415 ошибкой

Передавал в модель юзера просто 
```java
    model.addAttribute("userForm", new User());
```
