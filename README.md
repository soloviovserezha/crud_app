# Task 2.3.1 Crud-app

1) Не понял как правильно использововать @RequesBody
в контроллере. 

```java
    @PostMapping("/users/add")
    public String saveUserPost(@RequesBody User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
```
код валился с 415 ошибкой

Передавал в модель юзера просто и потом без @RequesBody уже работал с юзером.
```java
    model.addAttribute("userForm", new User());
```

2) Невнимально код просмотрел перед пушем, коммент не 
удалил
> "зачем в String saveUser(Model model) ты делаешь userService.getUserList();?"

3) Изменения сохраняются, но исключения 
отлавливать, придется в дао, поэтому, думаю,
этот слой тоже нужен для этой операции
>" А еще протестируй - что будет если вообще 
> не передавать юсера в дао для сохранения"


