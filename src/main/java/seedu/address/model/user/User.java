package seedu.address.model.user;

import seedu.address.model.credential.Username;

/**
 * General details of a User account.
 */
public abstract class User {
    protected Username username;
    protected Name name;
    protected Role role;
    protected PathToProfilePic pathToProfilePic;

    /**
     * Constructor method of User
     *
     * @param name             The name of the user.
     * @param role             The role of the user.
     * @param pathToProfilePic The path to the image to be used as profile picture.
     */
    public User(Username username, Name name, Role role,
                PathToProfilePic pathToProfilePic) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.pathToProfilePic = pathToProfilePic;
    }

    public abstract void updateName(String newName);

    public abstract void updateProfilePic(String newPath);

    //public abstract void deleteUser(User userToDelete);

    public Username getUsername() {
        return username;
    }

    public Name getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public PathToProfilePic getPathToProfilePic() {
        return pathToProfilePic;
    }

    public abstract void updatePassword (String newPassword);

}
