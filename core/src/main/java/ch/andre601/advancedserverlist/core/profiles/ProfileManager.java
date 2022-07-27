/*
 * MIT License
 *
 * Copyright (c) 2022 Andre_601
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package ch.andre601.advancedserverlist.core.profiles;

import ch.andre601.advancedserverlist.core.AdvancedServerList;

import java.util.HashMap;
import java.util.Map;

public class ProfileManager{
    
    private final AdvancedServerList core;
    
    private Map<String, Object> replacements = new HashMap<>();
    
    private ProfileManager(AdvancedServerList core){
        this.core = core;
    }
    
    public static ProfileManager get(AdvancedServerList core){
        return new ProfileManager(core);
    }
    
    public ProfileManager replacements(Map<String, Object> replacements){
        this.replacements = replacements;
        return this;
    }
    
    public ServerListProfile getProfile(){
        for(ServerListProfile profile : core.getFileHandler().getProfiles()){
            if(profile.getMotd().isEmpty() && profile.getPlayers().isEmpty() && profile.getPlayerCount().isEmpty())
                continue;
    
            ConditionsHolder conditions = profile.getConditions();
            conditions.replacements(replacements);
            if(conditions.eval(core.getPluginLogger()))
                return profile;
        }
        
        return null;
    }
}
