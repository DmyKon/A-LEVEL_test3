//Hi.
//Can you please create a cloud-ready database java application which will help to manage a hospital with
// patients affected by coronavirus.
//But there are some requirements for such app:
//1. App should allow to track patients. Besides, this entity of the patient should support dates as
// dates (not string), for example date of arriving at hospital must use LocalDateTime type, but not string.
//2. App should allow to manage doctors. For sure current status of any doctor must be indicated as enum,
// but not string
//3. App should allow to manipulate medicals and recipes.
//4. App should support all known by you relations among your entities.
//5. There are no strong requirements in terms of the amount of entities.
// Express yourself as experienced java database-engineers.
//6. Don’t forget about SOLID when you will be adding business-functions to your app.
//7. App must be deployed to Heroku.
//Thank you.
package ua.konstantynov.test3;

import ua.konstantynov.test3.user_interface.implement.MainMenu;

public class Main {
    public static void main(String[] args) {
        MainMenu.run();
    }
}