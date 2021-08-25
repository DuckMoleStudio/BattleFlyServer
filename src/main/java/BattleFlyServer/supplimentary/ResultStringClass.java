package BattleFlyServer.supplimentary;

import lombok.*;

//LOMBOK
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

// exactly as in client part
// (https://github.com/DuckMoleStudio/WinFormsApp02/blob/master/WinFormsApp02/ResultStringClass.cs)
public class ResultStringClass {
    public String name;
    public int team;
    public int sortieNo;
    public int roundsFired;
    public int hitsAchieved;
    public int hitsTaken;
    public int hitsGround;
    public int ffAir;
    public int ffGround;
    public int soloKills;
    public int killed;
    public int groupKills;
    public String killedBy;

}
