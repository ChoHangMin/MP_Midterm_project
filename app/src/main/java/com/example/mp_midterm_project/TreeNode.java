package com.example.mp_midterm_project;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import android.os.Parcel;
import android.os.Parcelable; // To transfer complex data object using Intent or Bundle.

public class TreeNode implements Parcelable { // temporary data type, TreeNode include String type playerName.
    String playerName;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode (String playerName){ // assign element to TreeNode's value various.
        this.playerName = playerName;

    }

    protected TreeNode(Parcel in) { // parcel객체를 읽어와 재구성
        playerName = in.readString(); // parcel에서 문자열을 읽어온다
        left = in.readParcelable(TreeNode.class.getClassLoader()); // parcel에서 parcelable 객체를 읽어온다
        right = in.readParcelable(TreeNode.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { // 객체를 parcel로 변환하는 구문
        dest.writeString(playerName);
        dest.writeParcelable(left, flags);
        dest.writeParcelable(right, flags);

    }

    @Override
    public int describeContents() { // 현 객체의 특별한 마스크 (대부분, 0) 반환
        return 0;
    }

    public static final Creator<TreeNode> CREATOR = new Creator<TreeNode>() { // Creator는 parcel객체를 생성하는데 필요한 정적변수
        @Override
        public TreeNode createFromParcel(Parcel in) { // parcel로 부터 객체 생성
            return new TreeNode(in);
        }

        @Override
        public TreeNode[] newArray(int size) { // 배열을 지정하여 Parcelable 객체 배열 생성
            return new TreeNode[size];
        }
    };

}

