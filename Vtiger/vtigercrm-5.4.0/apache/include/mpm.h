/* Copyright 2000-2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef APACHE_MPM_H
#define APACHE_MPM_H

/* mpm.h is the place to make declarations that are MPM specific but that must be 
 * shared with non-mpm specific code in the server.  Hummm, perhaps we can
 * move most of this stuff to mpm_common.h?
 */

#include "scoreboard.h"

#define MPM_NAME "WinNT"

#define AP_MPM_WANT_SET_PIDFILE
#define AP_MPM_WANT_SET_MAX_REQUESTS
#define AP_MPM_WANT_SET_COREDUMPDIR
#define AP_MPM_WANT_SET_SCOREBOARD
#define AP_MPM_WANT_SET_MAX_MEM_FREE

extern int ap_threads_per_child;
extern int ap_thread_limit;
extern server_rec *ap_server_conf;

#endif /* APACHE_MPM_H */
