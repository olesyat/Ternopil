package helsinki.assets.errors;

import helsinki.assets.Asset;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetErrorInteruptionReport.class)
@MapEntityTo
public class AssetErrorInteruptionReport extends AbstractPersistentEntity<String> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetErrorInteruptionReport.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

        @IsProperty
        @MapTo
        @Title(value = "Asset", desc = "Which asset has an error")
        private Asset asset;

        @IsProperty
        @MapTo
        @Title(value = "Regulatory", desc = "Desc")
        private boolean regulatory;

        @IsProperty
        @MapTo
        @Title(value = "KeyAsset", desc = "Desc")
        private boolean keyAsset;

        @IsProperty
        @MapTo
        @Title(value = "ErrorMessage", desc = "Desc")
        private String message;

        @Observable
        public AssetErrorInteruptionReport setErrorMessage(final String message) {
            this.message = message;
            return this;
        }

        public String getErrorMessage() {
            return message;
        }

        
        @Observable
        public AssetErrorInteruptionReport setKeyAsset(final boolean keyAsset) {
            this.keyAsset = keyAsset;
            return this;
        }

        public boolean getKeyAsset() {
            return keyAsset;
        }

        

        
        
        @Observable
        public AssetErrorInteruptionReport setRegulatory(final boolean regulatory) {
            this.regulatory = regulatory;
            return this;
        }

        public boolean getRegulatory() {
            return regulatory;
        }
        
        @Observable
        public AssetErrorInteruptionReport setAsset(final Asset asset) {
            this.asset = asset;
            return this;
        }

        public Asset getAsset() {
            return asset;
        }

    }



