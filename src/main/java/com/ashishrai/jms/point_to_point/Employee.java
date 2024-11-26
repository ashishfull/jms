
package com.ashishrai.jms.point_to_point;

import java.io.Serializable;

public record Employee(Integer id, String name, String phoneNumber) implements Serializable {
}
