/*
 * Copyright 2023 INDATACORE
 * Licensed under the INDATACORE License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License by Contacting INDATACORE
 * http://www.indatacore.com
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mat.api.core.errorhandling.businessexeption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.ws.rs.WebApplicationException;

@Getter
@Setter
public class BusinessException extends WebApplicationException {
    private static final long serialVersionUID = -3377325407569060574L;


    private HttpStatus status = HttpStatus.MULTI_STATUS;

    private transient BusinessErrorCode errorCode;

    private transient String message;

    public BusinessException(final BusinessErrorCode errorCode) {
        super(errorCode.getMessage());

        this.setErrorCode(errorCode);
    }

    public BusinessException(final String message, final BusinessErrorCode errorCode) {
        super(message + " " + errorCode.getMessage());
        this.setErrorCode(errorCode);
        this.setMessage(message);
    }

    public BusinessException(final String message) {
        super(message);
        this.setMessage(message);
    }
}
