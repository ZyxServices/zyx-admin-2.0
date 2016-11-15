package com.zyx.service.level;

import com.zyx.model.Level;

import java.util.Map;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

/**
 * Created by HL on 2016/11/11.
 */
public interface LevelService {

    Map<String,Object> insertLevel(Level level);

    Map<String,Object> delLevel(String ids);

    Map<String,Object> updateLevel(Level level);

    Map<String,Object> queryLevel(Level level);
}
