import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Crawler {


    public Item findSingleItem(String itemType, String itemName) throws IllegalArgumentException {
        if (!itemType.equals("book") && !itemType.equals("movie") && !itemType.equals("music")) {
            throw new IllegalArgumentException(String.format("Our API only supports looking for books, movies and music. You cannot look for items of type %1$s!", itemType));
        }

        if(itemName.length() < 1){
            throw new IllegalArgumentException("The name of the item that is being looked for cannot be an empty tring.");
        }

        return null;
    }
}
