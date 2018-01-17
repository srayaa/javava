package cn.javava.api.live.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 房间
 */
@Table(name = "jww_live_room")
@Entity
@DynamicUpdate
@Data
public class LiveRoomPo implements Serializable {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "ROOM_NAME")
    private String roomName;

    @Column(name = "ROOM_STATE")
    private String roomState;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
}
