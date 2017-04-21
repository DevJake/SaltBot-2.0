package me.Salt.Permissions;

import me.Salt.Exception.DuplicateDataException;
import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 15122390 on 21/04/2017.
 */
public class PermissionHandler {
    //TODO integrate into Configuration/SaltAPI
    private static HashMap<Long, Permission> ids; //Storing used IDs.
    private static List<Permission> permissions;

    public static List<Permission> getPermissions() {
        return permissions;
    }

    public static HashMap<Long, Permission> getIds() {
        return ids;
    }

    public static void registerPermission(Permission permission) throws DuplicateDataException {
        ids.put((long) permission.hashCode(), permission);
        if (!permissions.contains(permission)) permissions.add(permission);
        else throw new DuplicateDataException("Permission already exists! " + permission.getPermission());
    }

    public static boolean hasPermission(User user, Permission permission){
        //TODO
        return true;
    }
}
