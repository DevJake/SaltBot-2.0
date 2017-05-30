/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.Salt.Permissions;

import me.Salt.Exception.Generic.DuplicateDataException;
import me.Salt.SaltAPI.User.JUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class manages and handles all permissions. It is responsible for the registration of permissions,
 */
public class PermissionHandler {
    //TODO integrate into Configuration/SaltAPI
    private HashMap<Long, Permission> ids = new HashMap<>(); //Storing used IDs.
    private List<Permission> permissions = new ArrayList<>();
    
    public List<Permission> getPermissions() {
        return permissions;
    }
    
    public HashMap<Long, Permission> getIds() {
        return ids;
    }
    
    /**
     * This method allows for a permission to be added to the system. The Range controls what different ranges this
     * permission is able to function in. If a piece of code wishes to check a user's permissions, the system will
     * look at the range they specify. This will determine where and how the system should look for data.
     *
     * @param range      {@link Permission.Range} - The effective Range that this permission covers
     * @param permission {@link Permission} - The permission itself
     * @return PermissionHandler - The instance of this class, to allow for method chaining
     * @throws DuplicateDataException - If the list of permissions held within this class already contains the specified permission
     */
    public PermissionHandler registerPermission(Permission.Range range, Permission permission)
            throws DuplicateDataException {
        if (range.equals(Permission.Range.ALL)) {
            List<Permission.Range> ranges = new ArrayList<>(Arrays.asList(Permission.Range.values()));
            ranges.remove(Permission.Range.ALL);
            return registerPermission(ranges, permission); //Creates a new permission instance with all Range values
        }
        permission = new PermissionBuilder(permission).setRange(range).build();
        this.ids.put((long) permission.toString().hashCode(), permission);
        if (!this.permissions.contains(permission)) this.permissions.add(permission);
        else throw new DuplicateDataException("Permission already exists! " + permission.getPermission());
        return this;
        //TODO permissions that work on specific channels or guilds are to check a config file for the data they require. Data should -not- be placed into permissions themselves.
    }
    
    /**
     * This method serves the same purpose as
     * {@link PermissionHandler#registerPermission(Permission.Range, Permission)}, but allows for the specification
     * of multiple ranges.
     *
     * @param ranges     {@link Permission.Range} - The effective Ranges that this permission covers
     * @param permission {@link Permission} - The permission itself
     * @return PermissionHandler - The instance of this class, to allow for method chaining
     * @throws DuplicateDataException - If the list of permissions held within this class already contains the specified permission
     */
    public PermissionHandler registerPermission(List<Permission.Range> ranges, Permission permission)
            throws DuplicateDataException {
        // TODO: 30/05/2017 Integrate to use the ellipsis operator, removing the need for a method accepting a single range.
        for (Permission.Range r : ranges) {
            Permission p = new PermissionBuilder(permission).setRange(r).build();
            this.ids.put((long) p.toString().hashCode(), p);
            if (!this.permissions.contains(p)) this.permissions.add(p);
            else throw new DuplicateDataException("Permission already exists! " + permission.getPermission());
        }
        return this;
    }
    //    public boolean hasPermission(User user, Perm permission, Permission.Range range) {
    //        //TODO Check user has a permission, under a certain range.
    //    } //TODO do this (maybe, if needed...)
    
    /**
     * Returns a boolean representing if a specified user has a specified permission.
     *
     * @param user       {@link JUser} - The JUser to check permissions against
     * @param permission {@link Perm} - The permission to be checked
     * @return Boolean - If the user does or doesn't have the specified permission
     */
    public boolean hasPermission(JUser user, Perm permission) {
        //TODO if range is a null value, just return if the individual has a permission covering them -at all-, not to a specific range.
        return user.getPermissions().contains(permission);
    }
    // TODO: 30/05/2017 Get hasPermission to use elliptical operator to specify multiple perms
    
    /**
     * @param user       {@link JUser} - The JUser to check permissions against
     * @param permission {@link Perm} - The permission to be checked
     * @param range      {@link me.Salt.Permissions.Permission.Range} - The Range to check the permission against
     * @return Boolean - If the user does or doesn't have the specified permission
     */
    public boolean hasPermission(JUser user, Perm permission, Permission.Range range) {
        //TODO if range is a null value, just return if the individual has a permission covering them -at all-, not to a specific range.
        return user.getPermissions().contains(permission);
    }
    //    public Permission getPermission(Perm permission){
    //        //TODO
    //    }
}
