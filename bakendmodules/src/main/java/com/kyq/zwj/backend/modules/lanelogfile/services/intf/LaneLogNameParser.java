package com.kyq.zwj.backend.modules.lanelogfile.services.intf;

import com.kyq.zwj.backend.entity.LaneLogFileManager;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 15:55
 */
public interface LaneLogNameParser {
    LaneLogFileManager decodeFileParams(String fileName);
}
